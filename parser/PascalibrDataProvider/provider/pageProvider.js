'use strict'

const req = require('request')
const RequestUtils = require('../util/requestUtils')

class PageProvider {

  /**
   * @param url string
   */
  constructor(url) {
    this.url = url
  }

  /**
   * @returns {Promise<String>}
   */
  retrieve() {
    return new Promise((resolve, reject) => {
      req(this.url, (err, response, body) => {
        if (RequestUtils.responseIsOK(err, response)) {
          return reject(err)
        }

        resolve(body)
      })
    })
  }
}

module.exports = PageProvider