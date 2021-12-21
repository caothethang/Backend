import './contentAdmin.css'
import { useEffect, useState } from 'react'
import { Snackbar } from '@material-ui/core'
import { Alert } from '@mui/material'
import axios from 'axios'
import URL from '../url/url'

const ContentAdmin = () => {
    const [trees, setTrees] = useState([])
    const [tree, setTree] = useState()
    const [treeImage, setTreeImage] = useState()
    const [treeImageUrl, setTreeImageUrl] = useState('')
    const [treeId, setTreeId] = useState()
    const [treeName, setTreeName] = useState('')
    const [treeDescription, setTreeDescription] = useState('')
    const [treePrice, setTreePrice] = useState(0)
    const [treeCategoryId, setTreeCategoryId] = useState()
    const [upLoadNotifiContent, setUpLoadNotifiContent] = useState('')
    const [openSucces, setOpenSucces] = useState(false)
    const [openError, setOpenError] = useState(false)
    const [treeUpdate, setTreeUpdate] = useState()
    const [treeIdUpdate, setTreeIdUpdate] = useState()
    const [treeIdDelete, setTreeIdDelete] = useState()
    const [alertErrorContent, setAlertErrorContent] = useState('')
    const [alertSuccesContent, setAlertSuccesContent] = useState('')
    const [isActive, setIsActive] = useState(false)
    const [currentActive, setCurrentActive] = useState()


    const handleCloseError = () => {
        setOpenError(false)
    }

    const handleCloseSucces = () => {
        setOpenSucces(false)
    }
    const handleAddTree = () => {
        if (treeId && treeImageUrl && treeName && treeDescription && treePrice && treeCategoryId) {
            setTree({
                "categoryId": treeCategoryId,
                "description": treeDescription,
                "imageUri": treeImageUrl,
                "name": treeName,
                "price": treePrice,
                "quantity": 1,
                "userId": treeId
            })
            setAlertSuccesContent('Thêm cây thành công!')
            setOpenSucces(true)

        }
        else {
            setAlertErrorContent('Thêm cây thất bại!')
            setOpenError(true)
        }
    }

    const handleUpdate = () => {
        if (treeId && treeImageUrl && treeName && treeDescription && treePrice && treeCategoryId) {
            setTreeUpdate({
                "categoryId": treeCategoryId,
                "description": treeDescription,
                "imageUri": treeImageUrl,
                "name": treeName,
                "price": treePrice,
                "quantity": 1,
                "userId": treeId
            })
            setAlertSuccesContent('Cập nhật thành công!')
            setOpenSucces(true)
            RenderTrees()
        }
        else {
            setAlertErrorContent('Cập nhật thất bại!')
        }
    }

    const handleDelete = (id) => {
        setTreeIdDelete(id)
        setAlertErrorContent('Đã xoá!')
        RenderTrees()
        setOpenError(true)
    }

    const handleClickTree = (tree) => {
        setTreeId(tree.owner_id)
        setTreeName(tree.name)
        setTreeDescription(tree.description)
        setTreePrice(tree.price)
        setTreeCategoryId(tree.category_id)
        setTreeIdUpdate(tree.id)
        console.log(tree);

    }

    const RenderTrees = () => {
        axios.get(URL + '/tree')
            .then(res => {
                setTrees(res.data.data)
                console.log(trees);
            })
    }
    useEffect(() => {
        if (tree) {
            axios.post(URL + '/tree', tree)
                .then(() => {
                    setOpenSucces(true)
                    RenderTrees()
                })
        }
    }, [tree])

    useEffect(() => {
        axios.get(URL + '/tree')
            .then(res => {
                setTrees(res.data.data)
                console.log(trees);
            })
    }, [tree, treeUpdate, treeIdDelete])

    useEffect(() => {
        if (treeImage) {
            const fd = new FormData()
            fd.append('files', treeImage)
            axios.post(URL + '/images/upload', fd, {
                headers: {
                    'Content-Type': 'mutipart/form-data',
                },

            })
                .then(res => {
                    setTreeImageUrl(res.data)
                    console.log(res.data);
                    if (treeImageUrl) {
                        setUpLoadNotifiContent('Đã upload ảnh!')
                    }
                    else {
                        setUpLoadNotifiContent('Chưa upload ảnh!')
                    }
                })
        }
    }, [treeImage])

    useEffect(() => {
        if (treeUpdate) {
            axios.put(URL + '/tree/' + treeIdUpdate, treeUpdate)
                .then(() => {
                    console.log('updated!!!');
                    RenderTrees()
                })
        }
    }, [treeUpdate])

    useEffect(() => {
        if (treeIdDelete) {
            axios.delete(URL + '/tree/' + treeIdDelete)
                .then(() => {
                    RenderTrees()
                })
        }
    }, [treeIdDelete])

    return <>
        <div className='manager'>
            <div className='manager__product'>
                <h2 className='admin-title' >Quản lý sản phẩm</h2>
                <div className='manager__form'>
                    <div className='manager__title'>
                        <p>Mã người bán: </p>
                        <p>Tên sản phẩm: </p>
                        <p>Mô tả: </p>
                        <p>Giá tiền: </p>
                        <p>Loại cây: </p>
                        <p>Ảnh: </p>
                    </div>
                    <div className='manager__input'>
                        <input type='number' value={treeId} onChange={(e) => {
                            setTreeId(e.target.value)
                        }}></input><br />

                        <input type='text' value={treeName} onChange={(e) => {
                            setTreeName(e.target.value)
                        }}></input><br />

                        <input type='text' value={treeDescription} onChange={(e) => {
                            setTreeDescription(e.target.value)
                        }}></input><br />

                        <input type='number' value={treePrice} onChange={(e) => {
                            setTreePrice(e.target.value)
                        }}></input><br />

                        <input type='number' value={treeCategoryId} onChange={(e) => {
                            setTreeCategoryId(e.target.value)
                        }}></input><br />

                        <div className='input_file_form'>
                            <input type="file" className='input_file' onChange={(e) => {
                                if (e.target.files[0]) {
                                    setUpLoadNotifiContent('')
                                    setTreeImage(e.target.files[0])
                                }
                                console.log(e.target.files[0]);
                            }} />
                            {upLoadNotifiContent && <span>{upLoadNotifiContent}</span>}
                        </div>
                        {treeImageUrl && <img src={treeImageUrl} className='tree-image__preview'></img>}

                    </div>
                </div>
                <div className='btn-all'>
                    <button className='btn__manager btn__add' onClick={handleAddTree}>THÊM</button>
                    <button className='btn__manager btn__edit' onClick={handleUpdate}>SỬA</button>
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
                            <div className={tree.id === currentActive ? 'tree__item active' : 'tree__item'} key={tree.id} onClick={(e) => {
                                if(currentActive !== tree.id){
                                    setCurrentActive(tree.id)
                                    e.target.parentElement.classList.add('active')
                                }else {
                                    setCurrentActive()
                                }
                                handleClickTree(tree)
                            }}>
                                <p>{tree.id}</p>
                                <img src={tree.image_uri}></img>
                                <p>{tree.name}</p>
                                <p>{tree.price}</p>
                                <p>{tree.category_id}</p>
                                <button className='btn__delete' onClick={() => {
                                    handleDelete(tree.id)
                                }}>Xoá</button>
                            </div>
                        )
                    })}

                </div>
            </div>
        </div>
        <Snackbar
            anchorOrigin={{
                vertical: 'top',
                horizontal: 'left',
            }}
            open={openError}
            autoHideDuration={2000}
            onClose={handleCloseError}
        >
            <Alert variant="filled" severity="error" open={openError}>
                {alertErrorContent}
            </Alert>

        </Snackbar>

        <Snackbar
            anchorOrigin={{
                vertical: 'top',
                horizontal: 'left',
            }}
            open={openSucces}
            autoHideDuration={2000}
            onClose={handleCloseSucces}
        >
            <Alert variant="filled" severity="success" open={openSucces}>
                {alertSuccesContent}
            </Alert>

        </Snackbar>
    </>
}

export default ContentAdmin