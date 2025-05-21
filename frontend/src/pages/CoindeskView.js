import React, { useEffect, useState } from 'react';
import axios from 'axios';

function CoindeskView() {
  const [data, setData] = useState(null);

  useEffect(() => {
    axios.get('https://kengp3.github.io/blog/coindesk.json')
      .then(response => setData(response.data))
      .catch(error => console.error('Error fetching Coindesk data:', error));
  }, []);

  if (!data) return <p>載入中...</p>;

  const { time, bpi } = data;

  return (
    <div>
      <h2>Coindesk 匯率資訊</h2>
      <p>更新時間：{time.updated}</p>
      <table border="1">
        <thead>
          <tr>
            <th>幣別</th>
            <th>描述</th>
            <th>匯率</th>
            <th>符號</th>
          </tr>
        </thead>
        <tbody>
          {Object.keys(bpi).map(code => (
            <tr key={code}>
              <td>{code}</td>
              <td>{bpi[code].description}</td>
              <td>{bpi[code].rate}</td>
              <td dangerouslySetInnerHTML={{ __html: bpi[code].symbol }} />
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CoindeskView;
