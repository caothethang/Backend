import './contentOrder.css'
import URL from '../url/url'
import { useEffect, useState } from 'react'
import axios from 'axios'

const ContentOrder = () => {
    const [listOrder, setListOrder] = useState([])
    const renderOrderList = () => {
        axios.get(URL + '/order/1')
            .then(res => setListOrder(res.data.data))
    }

    const handleConfirm = (id) => {
        axios.put(URL + '/order/accept/' + id)
            .then(() => {
                renderOrderList()
            })
    }

    const handleReject = (id) => {
        axios.put(URL + '/order/reject/' + id)
            .then(() => {
                renderOrderList()
            })
    }

    useEffect(() => {
        axios.get(URL + '/order/1')
            .then(res => setListOrder(res.data.data))
    }, [])

    return <>
        <h1>DANH SÁCH ĐẶT HÀNG</h1>
        <div className='orderlist'>
            <div className='orderlist__title'>
                <p style={{ flex: '0.5' }}>STT</p>
                <p>Người mua</p>
                <p>Sản phẩm</p>
                <p>Ảnh sản phẩm</p>
                <p style={{ flex: '2' }}>Mô tả sản phẩm</p>
                <p>Số lượng</p>
                <p>Thành tiền</p>
                <p>Số điện thoại</p>
                <p>Địa chỉ</p>
                <p>Trạng thái</p>
            </div>
            <div className='orderlist__items'>
                {listOrder.map((order, index) => {
                    if (order.status === 2) {
                        return <div key={order.order_id} className='order__item'>
                            <p style={{ flex: '0.5' }}>{index + 1}</p>
                            <p>{order.customer_name}</p>
                            <p>{order.treeDTO.name}</p>
                            <img src={order.treeDTO.image_uri} alt='#'></img>
                            <p style={{ textAlign: 'justify', flex: '2' }}>{order.treeDTO.description}</p>
                            <p>{order.quantity}</p>
                            <p>{order.treeDTO.price * order.quantity} vnđ</p>
                            <p>{order.phone_number}</p>
                            <p>{order.description}</p>
                            <div>
                                <button className='btn__orders confirm' onClick={() => { handleConfirm(order.order_id) }}>Xác nhận</button><br />
                                <button className='btn__orders reject' onClick={() => { handleReject(order.order_id) }}>Từ chối</button>
                            </div>
                        </div>
                    }
                    if (order.status === 1) {
                        return <div key={order.order_id} className='order__item'>
                            <p style={{ flex: '0.5' }}>{index + 1}</p>
                            <p>{order.customer_name}</p>
                            <p>{order.treeDTO.name}</p>
                            <img src={order.treeDTO.image_uri} alt='#'></img>
                            <p style={{ textAlign: 'justify', flex: '2' }}>{order.treeDTO.description}</p>
                            <p>{order.quantity}</p>
                            <p>{order.treeDTO.price * order.quantity} vnđ</p>
                            <p>{order.phone_number}</p>
                            <p>{order.description}</p>
                            <p>Đã xác nhận</p>
                        </div>
                    }
                    else{
                        return <div key={order.order_id}></div>
                    }
                }
                )}
            </div>
        </div>
    </>
}

export default ContentOrder