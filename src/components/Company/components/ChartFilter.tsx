
interface filterProps{
    text: string,
    active: boolean,
    onClick: () => void
};

const ChartFilter = ({ text, active, onClick }: filterProps) => {
  return (
    <button style={{ color: active ? '#6425FE' : "#838383" }} onClick={onClick} className='chart-filter-btn'>
        {text}
    </button>
  )
}

export default ChartFilter