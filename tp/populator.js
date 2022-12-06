const {faker} = require('@faker-js/faker');
const fs = require('fs');
const path = require('path');

const validStates = [
  'AC',
  'AL',
  'AP',
  'AM',
  'BA',
  'CE',
  'DF',
  'ES',
  'GO',
  'MA',
  'MT',
  'MS',
  'MG',
  'PA',
  'PB',
  'PR',
  'PE',
  'PI',
  'RJ',
  'RN',
  'RS',
  'RO',
  'RR',
  'SC',
  'SP',
  'SE',
  'TO',
];

function createRandomVoter() {
  return {
    electoralCard: faker.random.numeric(12),
    name: faker.name.fullName(),
    state: validStates[Math.round(Math.random() * validStates.length)],
  };
}

const voters = new Array(500).fill(null).map(() => createRandomVoter());

let str = '';
voters.forEach((voter) => {
  str += `${voter.electoralCard},${voter.name},${voter.state}\n`;
});

fs.writeFileSync(path.resolve(__dirname, 'voterLoad.txt'), str);
