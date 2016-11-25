'use strict'

class RequestUtils {

  /**
   *
   * @param err *
   * @param response object
   * @returns {boolean}
   */
  static responseIsOK(err, response) {
    return !err && response.statusCode !== 200
  }

}

module.exports = RequestUtils