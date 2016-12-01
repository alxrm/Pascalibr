'use strict'

const ProviderUtils = require('../util/providerUtils')
const BaseParser = require('./baseParser')
const ListTransformer = require('../transformer/listTransfrormer')

const LIST_URL = 'http://pascalbook.ru/handbook/'

class ListParser extends BaseParser {

  constructor() {
    super()
    this.url = LIST_URL
  }

  /**
   * @returns Promise<Array>
   */
  parse() {
    const transform = new ListTransformer().transform

    return ProviderUtils
        .provideHtmlFrom(this.url)
        .then(transform)
  }
}

module.exports = ListParser