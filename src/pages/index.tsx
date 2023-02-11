import Head from 'next/head';
import Image from 'next/image';
import { Inter } from '@next/font/google';
import styles from '@/styles/Home.module.css';

export default function Home() {
  return (
    <>
      <Head>
        <title>MonoFi Marketplace</title>
        <meta name="description" content="MonoFi Marketplace" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta
          name="keywords"
          content="crypto, monofi, exchange, marketplace, nft"
        />
        <link rel="icon" href="/favicon.ico" />
      </Head>
    </>
  );
}
