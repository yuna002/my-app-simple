import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

function CurrencyList() {
  const [currencies, setCurrencies] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8090/currency')
      .then(res => setCurrencies(res.data))
      .catch(err => console.error(err));
  }, []);

  const deleteCurrency = (code) => {
    axios.delete(`http://localhost:8090/currency/${code}`)
      .then(() => setCurrencies(currencies.filter(c => c.code !== code)))
      .catch(err => console.error(err));
  };

  return (
    <div>
      <h2>幣別列表</h2>
      <table border="1">
        <thead>
          <tr>
            <th>代碼</th>
            <th>英文名稱</th>
            <th>中文名稱</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          {currencies.map(c => (
            <tr key={c.code}>
              <td>{c.code}</td>
              <td>{c.englishName}</td>
              <td>{c.chineseName}</td>
              <td>
                <Link to={`/edit/${c.code}`}>編輯</Link>
                {' | '}
                <button onClick={() => deleteCurrency(c.code)}>刪除</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CurrencyList;
