import './contentAdmin.css'
import { useEffect, useState } from 'react'
import axios from 'axios'
const ContentAdmin = () => {
    const [trees, setTrees] = useState([])
    useEffect(() => {
        axios.get('http://192.168.12.106:8080/api/tree')
            .then(res => setTrees(res.data.data))
    }, [])
    console.log(trees);
    return <>
        <div className='manager'>
            <div className='manager__product'>
                <h2 className='aaa'>Quản lý sản phẩm</h2>
                <div className='manager__form'>
                    <div className='manager__title'>
                        <p>Mã sản phẩm: </p>
                        <p>Tên sản phẩm: </p>
                        <p>Mô tả: </p>
                        <p>Giá tiền: </p>
                        <p>Loại cây: </p>
                        <p>Ảnh: </p>
                    </div>
                    <div className='manager__input'>
                        <input type='number'></input><br />
                        <input type='text'></input><br />
                        <input type='text'></input><br />
                        <input type='number'></input><br />
                        <input type='text'></input><br />
                    </div>
                </div>
                <div className='btn-all'>
                    <button className='btn__manager btn__add'>THÊM</button>
                    <button className='btn__manager btn__edit'>SỬA</button>
                </div>
            </div>
            <div className='list__product'>
                <h2 className='aaa' style={{ marginLeft: '-150px' }}>Danh sách sản phẩm</h2>
                <div className='manager__item__title'>
                    <p>Mã cây</p>
                    <p>Ảnh</p>
                    <p>Tên cây</p>
                    <p>Giá tiền</p>
                    <p>Loại cây</p>
                    <p></p>
                </div>
                <div className='list__trees'>

                    {trees.map(tree => {
                        if (tree.id > 1) {
                            return (
                                <div className='tree__item' key={tree.id}>
                                    <p>{tree.id}</p>
                                    <img src={tree.image_uri}></img>
                                    <p>{tree.name}</p>
                                    <p>{tree.price}</p>
                                    <p>{tree.category_id}</p>
                                    <button>Xoá</button>
                                </div>
                            )
                        }
                    })}

                </div>
            </div>
        </div>
    </>
}

export default ContentAdmin