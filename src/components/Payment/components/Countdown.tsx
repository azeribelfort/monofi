import React, { useEffect, useState } from 'react'
import { useRouter } from 'next/router'

function Countdown() {
    let [minute, setMinute] =useState(5)
    let [second1, setSecond1] = useState(0)
    let [second2, setSecond2] = useState(0)

    const router = useRouter()
    
    useEffect(() => {
        const interval = setInterval(() => {
          if (minute === 0 && second1 === 0 && second2 === 0) {
            clearInterval(interval);
            
            // NOTE: as soon as countdown finish Next-Router push
            // the root path to the address bar.

            router.push('/')

          } else if (second1 === 0 && second2 === 0) {
            setMinute((prevMinute) => prevMinute - 1);
            setSecond1(5);
            setSecond2(9);
          } else if (second2 === 0) {
            setSecond1((prevSecond1) => prevSecond1 - 1);
            setSecond2(9);
          } else {
            setSecond2((prevSecond2) => prevSecond2 - 1);
          }
        }, 1000);
    
        return () => clearInterval(interval);
      }, [minute, second1, second2]);
    

  return (
    <div className='Count'>
        <input value={0} type="number" disabled/>
        <input value={minute} type="number" disabled/>
        <p>:</p>
        <input value={second1} type="number" disabled/>
        <input value={second2}  type="number" disabled/>
    </div>
  )
}

export default Countdown