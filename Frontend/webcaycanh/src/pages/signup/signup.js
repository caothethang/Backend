import './signup.css'
import { Link } from 'react-router-dom'
import { Snackbar } from '@material-ui/core'
import { Alert } from '@mui/material'
import React, { useEffect, useState } from 'react'
import axios from 'axios'
import URL from '../../component/url/url'

function SignUp() {
    const [username, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const [account, setAccount] = useState({});
    const [repass, setRepass] = useState('')
    const [open, setOpen] = useState(false)
    const [notifiContent, setNotifiContent] = useState('')
    const [openSucces, setOpenSucces] = useState(false)
    const handleClick = () => {
        setOpen(true)
    }
    const handleClose = () => {
        setOpen(false)
    }
    const handleSignUp = async (e) => {
        e.preventDefault()

        if (username && password && repass && password === repass) {
            setAccount({
                username,
                password
            })
        }
        else {
            handleClick()
        }

    }

    useEffect(() => {
        axios.post(URL + '/user/register', {
            username,
            password
        })
            .then(res => {
                if (res.data.status === 201) {
                    setOpenSucces(true)
                }
                else {
                    setOpenSucces(false)
                    console.log(res.data.status);
                }
            })
    }, [account])
    return (
        <div>
            <form className='login__form'>
                <h1>TOTORO GARDEN</h1>
                <p>Tên tài khoản</p>
                <input className='signup__input' type='text' value={username} onChange={
                    (e) => {
                        setUserName(e.target.value)
                    }
                }></input>
                <p>Mật khẩu</p>
                <input className='signup__input' type='password' value={password} onChange={
                    (e) => {
                        setPassword(e.target.value)
                    }
                }></input><br />
                <p>Nhập lại mật khẩu</p>
                <input className='signup__input' type='password' value={repass} onChange={
                    (e) => {
                        setRepass(e.target.value)
                    }
                }></input><br />

                <div className='aaa'>
                    <Link to='/signup' className='btn_signup_form' onClick={handleSignUp}>Đăng ký</Link><br />
                    <Link to='/login' className='btn_signup_form'>Đăng nhập</Link>
                </div>

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
                    Đăng ký không thành công!
                </Alert>

            </Snackbar>

            <Snackbar
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                open={openSucces}
                autoHideDuration={2000}
                onClose={handleClose}
            >
                <Alert variant="filled" severity="success" open={openSucces}>
                    Đăng ký thành công!
                </Alert>

            </Snackbar>
        </div>
    )
}

export default SignUp