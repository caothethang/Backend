import './ContentProduct.css'

import { useEffect, useState } from 'react'
import axios from 'axios'
import Item from '../item/item'
import URL from '../url/url'

const ContentProduct = () => {
    const [products, setProducts] = useState([])

    useEffect(() => {
        axios.get(URL + '/tree')
            .then(res => setProducts(res.data.data))
    }, [])
    console.log('api: ', products , URL + '/api/tree');

    return (
        <div className='product__list_item'>
            {products.map((product) => {
                return <>
                    <Item data={product} key={product.id}></Item>
                </>
            })}
        </div>
    )
}
export default ContentProduct