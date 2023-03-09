import Header from '@/components/Header/Header';
import Sidebar from '@/components/SideBar/Sidebar';

interface props {
    children: React.ReactNode;
}

const Layout = ({ children }: props) => {
    return (
    <>
        <Header />
        <main className="container mw-100 mx-5">
            <div className="row">
                <div className="col-2">
                    <Sidebar />
                </div>
                <div className="col-9">
                    {children}
                </div>
            </div>
        </main>
    </>
    )
}

export default Layout