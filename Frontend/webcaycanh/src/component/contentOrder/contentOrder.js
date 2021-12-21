import './contentOrder.css'
import URL from '../url/url'
import { useEffect, useState } from 'react'
import axios from 'axios'

const ContentOrder = () => {
    const [listOrder, setListOrder] = useState([])

    useEffect(() => {
        axios.get(URL + '/order/1')
            .then(res => setListOrder(res.data.data))
    }, [])
    console.log(listOrder);
    return <>
        <h1>DANH SÁCH ĐẶT HÀNG</h1>
        <div className='orderlist'>
            <div className='orderlist__title'>
                <p>Mã tài khoản</p>
                <p>Người mua</p>
                <p>Sản phẩm</p>
                <p>Ảnh sản phẩm</p>
                <p style={{flex: '2'}}>Mô tả sản phẩm</p>
                <p>Số lượng</p>
                <p>Thành tiền</p>
                <p>Số điện thoại</p>
                <p>Địa chỉ</p>
            </div>
            <div className='orderlist__items'>
                {listOrder.map((order, index) =>
                    <div key={index} className='order__item'>
                        <p>{order.user_id}</p>
                        <p>{order.customer_name}</p>
                        <p>{order.treeDTO.name}</p>
                        <img src={order.treeDTO.image_uri} alt='#'></img>
                        <p style={{textAlign: 'justify', flex: '2'}}>{order.treeDTO.description}</p>
                        <p>{order.quantity}</p>
                        <p>{order.treeDTO.price * order.quantity}</p>
                        <p>{order.phone_number}</p>
                        <p>{order.description}</p>
                    </div>
                )}
            </div>
        </div>
    </>
}

export default ContentOrder