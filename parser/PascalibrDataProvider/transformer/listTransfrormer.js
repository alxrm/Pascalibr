'use strict'

const cheerio = require('cheerio')
const BaseTransformer = require('./baseTransformer')

class ListTransformer extends BaseTransformer {

  /**
   * @param raw string
   * @returns object
   */
  transform(raw) {
    const $ = cheerio.load(raw)

    return $('.subnav__ddown')
        .find('.subnav__link')
        .map((index, elem) => {
          return {
            name: $(elem).text(),
            link: $(elem).attr('href').replace('redesign.', '')
          }
        })
        .get()
  }
}

module.exports = ListTransformer