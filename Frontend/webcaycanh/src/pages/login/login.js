import './login.css'
import { Link } from 'react-router-dom'
import React, { useEffect, useState } from 'react'
import { Snackbar } from '@material-ui/core'
import { Alert } from '@mui/material'
import axios from 'axios'
import URL from '../../component/url/url'


function Login() {
    const [isAdmin, setIsAdmin] = useState(null)
    const [role, setRole] = useState(null)
    const [respon, setRespon] = useState(0)
    const [username, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const [open, setOpen] = useState(false)
    localStorage.setItem('isLogin', JSON.stringify({}))

    const handleClose = () => {
        setOpen(false)
    }

    const handleLogin = (e) => {
        if (respon.data) {
            setRole(respon.data.role)
            localStorage.setItem('isLogin', JSON.stringify({
                id: respon.data.id,
                name: respon.data.userName,
                role: respon.data.role
            }))
        }
        else {
            e.preventDefault()
            setOpen(true)
        }

    }

    useEffect(() => {
        axios.post(URL + '/user/login', {
            username,
            password
        })
            .then((res) => {
                setRespon(res.data)
                if(res.data.data){
                    setRole(res.data.data.role)
                    if(role === 1){
                        setIsAdmin(true)
                    }
                    else{
                        setIsAdmin(false)
                    }
                }
                
            })
    }, [username, password, role])
    return (
        <div>
            <form className='login__form'>
                <h1>TOTORO GARDEN</h1>
                <p>Tên tài khoản</p>
                <input className='login__input' type='text' value={username} onChange={
                    (e) => {
                        setUserName(e.target.value)
                    }
                }>
                </input>
                <p>Mật khẩu</p>
                <input className='login__input' type='password' value={password} onChange={
                    (e) => {
                        setPassword(e.target.value)
                    }
                }></input><br />
                <div className='div_btn'>
                    {/* <Link to={link} className='btn__login' onClick={handleLogin}>Đăng nhập</Link><br /> */}
                    {
                        isAdmin ? <Link to='/admin' className='btn__login' onClick={handleLogin}>Đăng nhập</Link> : <Link to='/' className='btn__login' onClick={handleLogin}>Đăng nhập</Link>
                    }
                </div>
                <Link to='/signup' className='btn__signup'>--- Đăng ký ---</Link>
            </form>
            <Snackbar
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                open={open}
                autoHideDuration={2000}
                onClose={handleClose}
            >
                <Alert variant="filled" severity="error" open={open}>
                    Đăng nhập thất bại!
                </Alert>
            </Snackbar>

        </div>
    )
}

export default Login