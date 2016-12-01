'use strict'

const cheerio = require('cheerio')
const BaseTransformer = require('./baseTransformer')

class TitleTransformer extends BaseTransformer {

  constructor(title) {
    super()
    this.title = title
  }

  /**
   * @param raw string
   * @returns object
   */
  transform(raw) {
    const $ = cheerio.load(raw)
    const titleText = $('.entry-title').text()
    const type = titleText.substr(0, titleText.indexOf(this.title))

    return { titleText, type }
  }

}

module.exports = TitleTransformer