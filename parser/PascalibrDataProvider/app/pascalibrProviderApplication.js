'use strict'

const ListParser = require('../parser/listParser')
const PageParser = require('../parser/pageParser')

const fs = require('fs')
const RES_DIR = './result/'

class PascalibrProviderApplication {

  constructor() {
    this.listParser = new ListParser()

    this.start()
  }

  start() {
    this.listParser.parse()
        .then(list => {
          return Promise.all(list.map(page => {
            const pageParser = new PageParser(page.link, page.name)

            return pageParser.parse()
          }))
        })
        .then(() => new Promise(resolve => fs.readdir(RES_DIR, (err, files) => resolve(files))))
        .then(files => {
          return Promise.all(files.map(fileName => {
            return new Promise(resolve => {
              fs.readFile(RES_DIR + fileName, 'utf8', (err, data) => {
                resolve({fileName, data: JSON.parse(data)})
              })
            })
          }))
        })
        .then(readFiles => {
          const catalog = readFiles.map(entry => {
            return {
              fileName: entry.fileName,
              name: entry.fileName.replace('.json', ''),
              type: entry.data.title.type
            }
          })

          fs.writeFile(`${RES_DIR}Catalog.json`, JSON.stringify(catalog))
        })
        .catch(err => {
          console.error(err)
        })
  }
}

module.exports = new PascalibrProviderApplication()