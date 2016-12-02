'use strict'

const cheerio = require('cheerio')
const BaseTransformer = require('./baseTransformer')

class DescriptionTransformer extends BaseTransformer {

  /**
   * @param raw string
   * @returns Array<Object>
   */
  transform(raw) {
    const $ = cheerio.load(raw)
    const descriptionContainer = $('.entry-content')

    return descriptionContainer.children()
        .map((index, elem) => {
          const data = $(elem).text()
          const type = this.entryType($, elem)

          return { type, data }
        })
        .get()
  }

  entryType($, elem) {
    if ($(elem).prev().text() === 'Пример:') return 'code'

    return elem.tagName === 'h4' ? 'subtitle' : 'text'
  }
}

module.exports = DescriptionTransformer