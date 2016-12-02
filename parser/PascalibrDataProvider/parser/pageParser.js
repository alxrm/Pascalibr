'use strict'

const ProviderUtils = require('../util/providerUtils')
const BaseParser = require('./baseParser')
const TitleTransformer = require('../transformer/titleTransformer')
const DescriptionTransformer = require('../transformer/descriptionTransformer')
const ModuleTransformer = require('../transformer/moduleTransformer')

class PageParser extends BaseParser {

  constructor(pageUrl, name) {
    super()

    this.url = pageUrl
    this.name = name

    this.processPage = this.processPage.bind(this)
  }

  /**
   * @returns Promise<object>
   */
  parse() {
    return ProviderUtils
        .provideHtmlFrom(this.url)
        .then(this.processPage)
  }

  /**
   *
   * @param rawData string
   * @returns object
   */
  processPage(rawData) {
    if (this.hasModule(rawData)) return null

    const title = this.processTitle(rawData)
    const description = this.processDescription(rawData)

    return {
      title, description
    }
  }

  /**
   * @param rawData string
   * @returns object
   */
  processTitle(rawData) {
    return new TitleTransformer(this.name).transform(rawData)
  }

  /**
   * @returns object
   */
  processDescription(rawData) {
    return new DescriptionTransformer().transform(rawData)
  }

  /**
   * @returns boolean
   */
  hasModule(rawData) {
    const module = new ModuleTransformer().transform(rawData)

    return module.indexOf('Модуль') !== -1
  }
}

module.exports = PageParser