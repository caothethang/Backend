import { Link } from 'react-router-dom'
import './nav.css'

const Nav = () => {
    return(
        <div className='nav'>
            <ul>
                <li><Link to='/'>Trang chủ</Link></li>
                <li><Link to='/product'>Sản phẩm</Link></li>
                <li><a href='#'>Kiến thức cây cảnh</a></li>
                <li><a href='#'>Giới thiệu</a></li>
                <li><a href='#'>Liên hệ</a></li>
            </ul>
        </div>
    )
}

export default Nav