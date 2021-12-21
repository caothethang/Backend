import './ContentHome.css'
import { useEffect, useState } from 'react'
import axios from 'axios'
import URL from '../url/url'
import Item from '../item/item'

const ContentHome = () => {
    const contentIntroduceText = 'Totoro Garden - Uy tín vươn xa là thương hiệu trong lĩnh vực cây cảnh mini, sen đá - xương rồng, cây nội thất tại Hà Nội. Ra đời từ năm 2017, tới nay Totoro Garden đã phục vụ 30.000+ khách hàng. Triết lý kinh doanh của Chúng tôi là “Lan tỏa sức sống” và “Là bạn đồng hành” với khách hàng. Chúng tôi coi công việc của mình luôn mạng lại màu xanh tươi cho cuộc sống và kiến tạo giá trị, sức sống cho mọi không gian mà chúng tôi bước tới. Sự tin tưởng của Quý khách hàng trong suốt thời gian qua là niềm tự hào và là động lực để Totoro Garden tiếp tục lớn mạnh và phát triển.'
    const [newProduct, setNewProduct] = useState([])
    const [count, setCount] = useState(0)
    useEffect(() => {
        axios.get(URL + '/tree')
            .then(res => setNewProduct(res.data.data))
    }, [])
    return (
        <div className='content'>
            <h1>CHÚNG TÔI LÀ TOTORO GARDEN</h1>
            <div className='content__introduce'>
                <div className='content__image'>
                    <img src='https://totorogarden.com/wp-content/uploads/2020/04/36161386_206404653350686_5475557121661599744_n-240x300-1.jpg'></img>
                </div>
                <p>{contentIntroduceText}</p>
            </div>
            <div className='content__new_product'>
                <h1>SẢN PHẨM MỚI</h1>
                <div className='content__list_item'>
                    {newProduct.map((item, index) => {
                        if (index < 4) {
                            return <Item data={item} key={item.id} />
                        }
                    })}
                </div>
            </div>

            <h1>BÁN CHẠY NHẤT</h1>
            <div className='content__list_item'>
                {newProduct.map((item, index) => {
                    if (index < 4) {
                        return <Item data={item} key={item.id} />
                    }
                })}
            </div>

        </div>
    )
}

export default ContentHome