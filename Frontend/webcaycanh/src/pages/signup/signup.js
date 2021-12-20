import './signup.css'
import { Link } from 'react-router-dom'
import { Snackbar } from '@material-ui/core'
import { Alert } from '@mui/material'
import React, { useState } from 'react'
import axios from 'axios'

function SignUp() {
    const [username, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const [repass, setRepass] = useState('')
    const [open, setOpen] = useState(false)
    const handleClick = () => {
        setOpen(true)
    }
    const handleClose = () => {
        setOpen(false)
    }
    const handleSignUp = async (e) => {
        if(username && password && repass && password === repass){
            
        }
        else{
            e.preventDefault()
            handleClick()
        }
    }

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
                <Link to='/login' className='btn__login' onClick={handleSignUp}>Đăng ký</Link>
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
        </div>
    )
}

export default SignUp