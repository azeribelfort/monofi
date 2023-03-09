import Apple from './assets/companies/apple.png'
import Birbank from './assets/companies/birbank.png'
import Dersevi from './assets/companies/dersevi.png'
import Yelobank from './assets/companies/yelobank.png'

export const mockCompanies = [
    {
        id: 1,
        name: 'Apple',
        abbrv: 'AAPL',
        price: '$220',
        isIncreased: true,
        change: '14%',
        investors: 120000,
        highestBid: '$3.5 bn.',
        outright: '$4 bn.',
        imgSrc: Apple,
    },
    {
        id: 2,
        name: 'Birbank',
        abbrv: 'BIRBNK',
        price: '$220',
        isIncreased: false,
        change: '8%',
        investors: 120000,
        highestBid: '$3.5 bn.',
        outright: '$4 bn.',
        imgSrc: Birbank,
    },
    {
        id: 3,
        name: 'Ders Evi',
        abbrv: 'DRSVI',
        price: '$220',
        isIncreased: true,
        change: '27%',
        investors: 120000,
        highestBid: '$3.5 bn.',
        outright: '$4 bn.',
        imgSrc: Dersevi,
    },
    {
        id: 4,
        name: 'yelobank',
        abbrv: 'YLBNK',
        price: '$220',
        isIncreased: false,
        change: '16%',
        investors: 120000,
        highestBid: '$3.5 bn.',
        outright: '$4 bn.',
        imgSrc: Yelobank,
    },
];
