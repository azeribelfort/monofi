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
<<<<<<< HEAD
      <Landing />
=======
      <Header />
      <main className="container mw-100 mx-5">
        <div className="row">
          <div className="col-2">
            <Sidebar />
          </div>
          <div className="col-9">
            {/* <Layout /> */}
            <Dashboard />

          </div>
        </div>
      </main>
>>>>>>> 1f61e4cfbf38fc1a09a0c2a524d0116bce3ba7c0
    </>
  );
}
