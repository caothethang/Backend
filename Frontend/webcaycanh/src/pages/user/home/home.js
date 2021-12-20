import Footer from "../../../component/footer/footer"
import Header from "../../../component/header/header"
import Content from "../../../component/content/content"
import { BrowserRouter } from 'react-router-dom'

function Home() {

    return (
        <div>
            <Header />
            <Content />
            <Footer />
        </div>
    )
}

export default Home