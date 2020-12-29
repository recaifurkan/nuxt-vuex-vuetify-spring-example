

You probably don't wanna use ts-node, because it is slow, instead follow following steps for fast .ts files compilation (Make sure node is installed):

    npm i -D @types/node typescript nodemon

    npx tsconfig.json and select node from the list. You are free to modify it as per your needs.

    Create a file names src/index.ts in your project root.

    Then in your package.json, add the following 2 scripts:

    {
  "name": "whatsapp",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "watch": "tsc -w",
    "dev": "tsc -w & nodemon dist/index.js"
  },
  "author": "",
  "license": "ISC"
}


    Then use:

    npm run watch

    npm run dev

And, it will automatically look for changes in .ts files and you can see the compiled file output in the terminal as you go!
