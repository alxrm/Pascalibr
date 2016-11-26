/**
 * Created by alex on 26/11/2016.
 */

'use strict'

class BaseParser {

  /**
   * @template T
   * @returns Promise<T>
   */
  parse() {
    return new Promise()
  }
}

module.exports = BaseParser