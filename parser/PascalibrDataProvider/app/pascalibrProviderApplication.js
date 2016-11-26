'use strict'

const ListParser = require('../parser/listParser')

class PascalibrProviderApplication {

  constructor() {
    this.listParser = new ListParser()
  }

  start() {
    this.listParser.parse()
  }
}

module.exports = new PascalibrProviderApplication()