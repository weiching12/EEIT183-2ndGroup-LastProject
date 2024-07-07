$(document).ready(function () {
  // 初始化 DataTable
  let myTable = $("#propSellOrderTable").DataTable();

  const selGameSub = document.querySelector("#selGameSub");
  selGameSub.addEventListener("click", (event) => {
    event.preventDefault(); // 取消 form 預設的送出 (form 內有 submit 要寫，不然表單會送出)
    let SelectGame = document.querySelector("#SelectGame");
    let selectedOption = SelectGame.options[SelectGame.selectedIndex];
    const selectedGameId = selectedOption.value;
    console.log(selectedGameId);

    axios({
      method: "get",
      url: "http://localhost:8080/PlayCentric/prop/findAllpropSellOrder",
      params: {
        gameId: selectedGameId,
      },
    })
      .then((res) => {
        console.log(res.data); // 打印接收到的数据
        htmlMaker(res.data, myTable);
      })
      .catch((err) => {
        console.log(err);
      });
  });
});

function htmlMaker(data, table) {
  table.clear(); // 清空 DataTable 的現有內容

  data.forEach((item) => {
    console.log(item); // 打印每个数据项以进行调试

    // 判斷委託單狀態
    let orderStatusText;
    switch (item.orderStatus) {
      case 0:
        orderStatusText = "販賣中";
        break;
      case 1:
        orderStatusText = "已售出";
        break;
      case 2:
        orderStatusText = "已下架";
        break;
      default:
        orderStatusText = "未知";
    }

    // 查找道具名稱和圖片
    axios({
      method: "get",
      url: "http://localhost:8080/PlayCentric/prop/findById",
      params: { id: item.memberPropInventoryDto.propId },
    })
      .then((response) => {
        const propName = response.data.propName; // 假设道具名稱字段为 propName
        const propImageId = response.data.propImageId; // 假设图片ID字段为 propImageId

        table.row
          .add([
            item.orderId,
            propName, // 使用查找到的道具名稱
            `<img src="${window.location.origin}/PlayCentric/prop/image?id=${propImageId}" alt="${propName}" width="40px">`, // 假设图片路径可以通过 propImageId 构建
            item.quantity,
            item.amount,
            item.saleTime,
            item.expiryTime,
            item.sellerMemId,
            orderStatusText,
          ])
          .draw(false); // 将数据行添加到 DataTable 中

        // 重新繪製 DataTable
        table.draw();
      })
      .catch((err) => {
        console.log(err);
      });
  });
}
