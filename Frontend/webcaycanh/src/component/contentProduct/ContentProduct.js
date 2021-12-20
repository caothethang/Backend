import './ContentProduct.css'

import  {useEffect, useState} from 'react'
import axios from 'axios'
import Item from '../item/item'

const ContentProduct = () => {
    const [products, setProducts] = useState([])

    useEffect(() => {
        axios.get('http://192.168.12.106:8080/api/tree')
            .then(res => setProducts(res.data.data))
    }, [])
    console.log('api: ', products);

    return (
        <div className='product__list_item'>
            {products.map((product) => {
                if(product.id > 1){
                    return<>
                        <Item data={product} key={product.id}></Item>
                    </>
                }
            })}
        </div>
    )
}
export default ContentProduct