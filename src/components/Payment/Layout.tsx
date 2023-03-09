import React, { ReactNode } from 'react'
import Safebox from '../../assets/Payment/Safebox.svg'
import Key from '../../assets/Payment/GoldenKey.svg'
import Bag from '../../assets/Payment/Moneybag.svg'

function Layout({children} : {
    children: ReactNode
}) {
  return (
    <div className='Payment'>
        <div className='sect'>
            {children}
        </div>
    </div>
  )
}

export default Layout