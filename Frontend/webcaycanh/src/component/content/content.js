import { Routes, Route } from 'react-router-dom'

import ContentHome from "../contentHome/ContentHome";
import ContentProduct from '../contentProduct/ContentProduct';

const Content = () => {
    return (
        <div>
            <Routes>
                <Route path='/' element={<ContentHome/>}/>
                <Route path='/product' element={<ContentProduct/>}/>
            </Routes>
        </div>
    )
}

export default Content