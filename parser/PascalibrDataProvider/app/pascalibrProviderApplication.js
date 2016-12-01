'use strict'

const ListParser = require('../parser/listParser')
const PageParser = require('../parser/pageParser')

class PascalibrProviderApplication {

  constructor() {
    this.listParser = new ListParser()

    this.start()
  }

  start() {
    this.listParser.parse().then(list => {
      const pageParser = new PageParser(list[0].link, list[0].title)

      pageParser.parse()
    })
  }
}

module.exports = new PascalibrProviderApplication()