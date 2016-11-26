'use strict'

const req = require('request')
const RequestUtils = require('../util/requestUtils')

class RawPageProvider {

  /**
   * @param url string
   */
  constructor(url) {
    this.url = url
  }

  /**
   * @returns {Promise<String>}
   */
  provide() {
    return new Promise((resolve, reject) => {
      req(this.url, (err, response, body) => {
        if (RequestUtils.responseIsOK(err, response)) {
          reject(err)
        }
        else {
          resolve(body)
        }
      })
    })
  }
}

module.exports = RawPageProvider