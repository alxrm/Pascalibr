'use strict'


const ProviderUtils = require('../util/providerUtils')
const BaseParser = require('./baseParser')
const TitleTransformer = require('../transformer/titleTransformer')

class PageParser extends BaseParser {

  constructor(pageUrl, title) {
    super()
    this.url = pageUrl
    this.title = title
  }

  /**
   * @returns Promise<object>
   */
  parse() {
    return ProviderUtils
        .provideHtmlFrom(this.url)
        .then(this.parseTitle)
  }

  /**
   * @param rawData string
   * @returns object
   */
  parseTitle(rawData) {
    const titleTransformer = new TitleTransformer(this.title)

    console.log(titleTransformer.transform(rawData))

    return titleTransformer.transform(rawData)
  }

  /**
   * @returns object
   */
  parseDescription(rawData) {

  }

  /**
   * @returns object
   */
  parseModuleData(rawData) {

  }
  /**
   * @returns object
   */
  parseCodeSnippet(rawData) {

  }
}

module.exports = PageParser