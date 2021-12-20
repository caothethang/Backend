import './contentAdmin.css'
import { useEffect, useState } from 'react'
import axios from 'axios'
import URL from '../url/url'

const ContentAdmin = () => {
    const [trees, setTrees] = useState([])
    const [treeImage, setTreeImage] = useState()
    const [treeImageUrl, setTreeImageUrl] = useState('')
    const [treeId, setTreeId] = useState()
    const [treeName, setTreeName] = useState('')
    const [treeDescription, setTreeDescription] = useState('')
    const [treePrice, setTreePrice] = useState(0)
    const [treeCategoryId, setTreeCategoryId] = useState()

    useEffect(() => {
        axios.get(URL + '/tree')
            .then(res => setTrees(res.data.data))
    }, [])

    useEffect(() => {
        const fd = new FormData()
        fd.append('files', treeImage)
        axios.post(URL + '/images/upload', fd)
            .then(res => console.log('respon' + res))
    }, [treeImage])


    return <>
        <div className='manager'>
            <div className='manager__product'>
                <h2 className='admin-title' >Quản lý sản phẩm</h2>
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
                        <input type='number' onChange={(e) => {
                            setTreeId(e.target.value)
                        }}></input><br />

                        <input type='text' onChange={(e) => {
                            setTreeName(e.target.value)
                        }}></input><br />

                        <input type='text' onChange={(e) => {
                            setTreeDescription(e.target.value)
                        }}></input><br />

                        <input type='number' onChange={(e) => {
                            setTreePrice(e.target.value)
                        }}></input><br />

                        <input type='number' onChange={(e) => {
                            setTreeCategoryId(e.target.value)
                        }}></input><br />

                        <input type="file" className='input_file' onChange={(e) => {
                            setTreeImage(e.target.files[0])
                            console.log(e.target.files[0]);
                        }}/>
                    </div>
                </div>
                <div className='btn-all'>
                    <button className='btn__manager btn__add'>THÊM</button>
                    <button className='btn__manager btn__edit'>SỬA</button>
                </div>
            </div>
            <div className='list__product'>
                <h2 className='admin-title' style={{ marginLeft: '-150px' }}>Danh sách sản phẩm</h2>
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
                        return (
                            <div className='tree__item' key={tree.id}>
                                <p>{tree.id}</p>
                                <img src={tree.image_uri}></img>
                                <p>{tree.name}</p>
                                <p>{tree.price}</p>
                                <p>{tree.category_id}</p>
                                <button className='btn__delete'>Xoá</button>
                            </div>
                        )
                    })}

                </div>
            </div>
        </div>
    </>
}

export default ContentAdmin