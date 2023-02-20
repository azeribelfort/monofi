import React from 'react'


function buttonSubmit(e) {
  e.preventDefault();
}

function Newsletter() {
  return (
    <div className='Newsletter'>
      <p>Join Our Newsletter</p>
      <form>
        <input type="email" placeholder='Your Email' />
        <button onSubmit={() => buttonSubmit()}>SUBMIT</button>
      </form>
    </div>
  )
}

export default Newsletter