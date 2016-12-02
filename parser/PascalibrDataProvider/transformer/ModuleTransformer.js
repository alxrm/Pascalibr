'use strict'

const cheerio = require('cheerio')
const BaseTransformer = require('./baseTransformer')

class ModuleTransformer extends BaseTransformer {

  /**
   * @param raw string
   * @returns boolean
   */
  transform(raw) {
    const $ = cheerio.load(raw)

    return $('.sidebar__title').text()
  }
}

module.exports = ModuleTransformer