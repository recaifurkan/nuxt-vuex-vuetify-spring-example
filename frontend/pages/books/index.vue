<template>
  <div>
    <v-card max-width="450" class="mx-auto">
      <v-toolbar color="cyan" dark>
        <v-toolbar-title>{{ $t("book.books") }}</v-toolbar-title>
        <v-btn @click="openNew" block>
          {{ $t("book.createBook") }}
        </v-btn>

        <v-spacer></v-spacer>
      </v-toolbar>

      <v-list three-line>
        <template v-for="(book, index) in allBooks">
          <v-list-item :key="book.id">
            <v-list-item-content @click="openUpdate(book)">
              <v-list-item-title v-html="book.title"></v-list-item-title>
              <v-list-item-subtitle v-html="book.isbn"></v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-action>
              <v-btn @click="deleteBook(book)" icon>
                <v-icon color="grey lighten-1">mdi-delete</v-icon>
              </v-btn>
            </v-list-item-action>
          </v-list-item>
        </template>
      </v-list>
    </v-card>

    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">
              <span v-if="dialogType == 'new'">
                {{ $t("book.createBook") }}</span
              >
              <span v-else> {{ $t("book.updateBook") }}</span>
            </span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <input
                  v-if="dialogType == 'update'"
                  type="hidden"
                  name="measureTime"
                  v-model="book.id"
                />
                <v-col cols="12" sm="6" md="4">
                  <v-text-field
                    v-model="book.title"
                    label="Kitap İsmi Giriniz*"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                  <v-text-field
                    v-model="book.isbn"
                    label="İsbn Giriniz*"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
            <small>* ile işaretlilerin girilmesi zorunludur.</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="closeDialog">
              {{ $t("close") }}
            </v-btn>
            <v-btn color="blue darken-1" text @click="processBook">
              {{ $t("save") }}
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  middleware: ["auth"],
  data: () => ({
    dialogType: "",
    dialog: false,
    book: {
      id: "",
      title: "",
      isbn: ""
    }
  }),

  async created() {},

  computed: {
    ...mapGetters("books", ["allBooks"])
  },
  methods: {
    ...mapActions("books", ["setBooks", "addBook", "updateBook", "deleteBook"]),

    openNew() {
      this.book = {};
      this.dialogType = "new";
      this.dialog = true;
    },

    openUpdate(book) {
      this.dialogType = "update";
      this.book = book;
      this.dialog = true;
    },
    closeDialog() {
      this.dialog = false;
    },
    processBook() {
      let book = null;
      switch (this.dialogType) {
        case "new":
          book = {
            title: this.book.title,
            isbn: this.book.isbn
          };
          console.log(book);
          this.addBook(book);

          break;

        case "update":
          book = {
            id: this.book.id,
            title: this.book.title,
            isbn: this.book.isbn
          };
          console.log(book);
          this.updateBook(book);
          break;

        default:
          break;
      }

      this.closeDialog();
    },
    saveBook() {
      const book = {
        title: this.book.title,
        isbn: this.book.isbn
      };
      console.log(book);
      this.addBook(book);
      this.closeDialog();
    }
  },
  async mounted() {
    await this.setBooks();
  },

  async fetch() {},

  async asyncData() {}
};
</script>
