module.exports = {
  locales: [
    {
      code: "en",
      iso: "en-US",
      name: "English",
      file: "en.js"
    },
    {
      code: "tr",
      iso: "tr-TR",
      name: "Turkish",
      file: "tr.js"
    }
  ],
  defaultLocale: "en",
  seo: false,
  lazy: true,
  detectBrowserLanguage: false,
  langDir: "i18n/",
  parsePages: false,
  pages: {
    about: {
      de: "/ueber-uns/",
      en: "/about-us/"
    },
    work: {
      de: "/referenzen/",
      en: "/work/"
    },
    legal: {
      de: "/impressum/",
      en: "/legal/"
    },
    disclaimer: {
      de: "/haftungsausschluss/",
      en: "/disclaimer/"
    },
    privacy: {
      de: "/datenschutz/",
      en: "/privacy/"
    }
  },
  vueI18n: {
    fallbackLocale: "en"
  }
};
