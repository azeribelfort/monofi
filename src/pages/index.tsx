import Head from 'next/head';
import Header from '@/components/Header/Header';
import Sidebar from '@/components/SideBar/Sidebar';
import Layout from '@/components/Main/Layout';

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
      <Header />
      <main className="container mw-100 mx-5">
        <div className="row">
          <div className="col-2">
            <Sidebar />
          </div>
          <div className="col-9">
            <Layout />
          </div>
        </div>
      </main>
    </>
  );
}
