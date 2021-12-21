import './header.css'
import Nav from '../nav/nav'
import { Link } from 'react-router-dom'

const Header = () => {
    var isLogin = false
    if(localStorage.getItem('isLogin')){
        isLogin = true
    }
    var user = []
    if(isLogin){
        user = [...user, JSON.parse(localStorage.getItem('isLogin'))]
        console.log(user);
    }

    const handelLogout = () => {
        localStorage.setItem('isLogin', JSON.stringify({}))
    }
    console.log(user.name);
    
    return (
        <div className="header">
            <div className='header__content'>
                <div className='logo__header'>
                    <img className='logo' src='https://totorogarden.com/wp-content/uploads/2020/04/rsz_logo-01.png'></img>
                </div>
                <input type={'search'} className='header__search' placeholder='Tìm kiếm'></input>
                {
                    user[0].name === undefined ? 
                    <span className='account'>
                        <Link to='/login'>Đăng nhập</Link>
                        <Link to='/signup'>Đăng ký</Link>
                    </span> : <span className='account'>
                        <a href='#'>{user[0].name}</a>
                        <Link to='/login' onClick={handelLogout}>Đăng xuất</Link>
                    </span>
                    
                }  
                    
            </div> 
                      
            <Nav></Nav>
        </div>
    )
}

export default Header