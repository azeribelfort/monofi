import SearchIcon from '../../../assets/Header/SearchIcon.svg';

const Search = () => {
  return (
    <div className="input-group">
      <span className="input-group-text bg-black text-white search-bar__icon">
        <SearchIcon />
      </span>
      <input
        type="text"
        className="form-control bg-black text-white search-bar__input"
        placeholder="Search"
      />
    </div>
  );
};

export default Search;
