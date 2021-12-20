import './contentCart.css'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { Snackbar } from '@material-ui/core'
import { Alert } from '@mui/material'
import URL from '../url/url'

const ContentCard = () => {
    let cartProduct = []
    const [open, setOpen] = useState(false)
    const [alertError, setAlertError] = useState('')
    const [cartProducts, setCartProducts] = useState([])
    const [quanlity, setQuanlity] = useState(1)
    const [name, setName] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [address, setAddress] = useState('')
    
    const handleClose = () => {
        setOpen(false)
    }
        
    useEffect(() => {
        axios.get(URL + '/tree')
            .then(res => setCartProducts(res.data.data))
    }, [])
    
    const id = JSON.parse(localStorage.getItem('product_cart'))
    cartProducts.map(x => {
        if (x.id === id) {
            cartProduct = x
        }
    })

    return <>
        <div className='content-cart'>
            <img className='cart-product__img' src={cartProduct.image_uri}></img>
            <div className='cart-product__content'>
                <h2 className='cart-product__title'>{cartProduct.name}</h2>
                <p className='cart-product__description'>{cartProduct.description + cartProduct.description + cartProduct.description}</p>
                <div className='cart-product__quanlity'>
                    <p>Số lượng: </p>
                    <button className='btn__product-quanlity' onClick={() => {
                        if (quanlity > 0) {
                            setQuanlity(prev => prev - 1)
                        }
                    }}>-</button>
                    
                    <p>{quanlity}</p>
                    <button className='btn__product-quanlity' onClick={() => {
                        setQuanlity(prev => prev + 1)
                    }}>+</button>
                    
                </div>
                <p className='cart-product__cost'>Thành tiền: {cartProduct.price * quanlity} vnđ</p>

                <button className='cart-product__btn-bought' onClick={() => {
                    if(quanlity === 0){
                        setAlertError('Vui lòng đặt lại số lượng mua!')
                        setOpen(true)
                    }
                    if(!name || !address || !phoneNumber){
                        setAlertError('Vui lòng nhập lại thông tin mua hàng!')
                        setOpen(true)
                    }
                }}>MUA NGAY</button>

            </div>
        </div>
        <h2 style={{color: '#436d4d'}}>Địa chỉ nhận hàng</h2>
        <div className='cart__userInfo'>
            <div className='xxx'>
                <div className='xx'>
                    <p>Họ tên</p>
                    <input type='text' value={name} onChange={(e) => {
                        setName(e.target.value)
                    }}/>
                </div>
                <div className='xx'>
                    <p>Số điện thoại</p>
                    <input type='text' value={phoneNumber} onChange={(e) => {
                        setPhoneNumber(e.target.value)
                    }}/>
                </div>
            </div>
            <div className='xx'>
                <p>Địa chỉ nhận hàng</p>
                <textarea value={address} onChange={(e) => {
                    setAddress(e.target.value)
                }}></textarea>
            </div>
        </div>
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
                {alertError}
            </Alert>
        </Snackbar>

    </>
}

export default ContentCard