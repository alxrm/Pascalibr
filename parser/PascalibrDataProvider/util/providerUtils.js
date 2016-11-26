'use strict'

const RawPageProvider = require('../provider/rawPageProvider');

class ProviderUtils {

  /**
   * @param url String
   * @returns Promise<String>
   */
  static provideHtmlFrom(url) {
    return new RawPageProvider(url).provide()
  }
}

module.exports = ProviderUtils