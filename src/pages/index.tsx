import Landing from '@/components/Landing/Landing';
import Head from 'next/head';

export default function Home() {
  return (
    <>
      <Head>
        <title>MonoFi</title>
        <meta name="description" content="MonoFi" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta
          name="keywords"
          content="crypto, monofi, exchange, marketplace, nft"
        />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <Landing />
    </>
  );
}
