// 初始化 DataTable 並設置語言選項
var table;

$(document).ready(function () {
  table = $("#myTable").DataTable({
    language: {
      info: "顯示第 _START_ 到第 _END_ 筆，共 _TOTAL_ 筆記錄",
    },
  });

  loadGames();
  loadAllPropTypes();

  $("#submitBtn").click(function (event) {
    event.preventDefault();
    const selectedGameId = $("#SelectGame").val();
    fetchPropTypesByGameId(selectedGameId, function (propTypes) {
      populatePropTypeSelect(propTypes);
      propTypes.forEach(function (propType) {
        propTypesMap[propType.propTypeId] = propType.propType;
      });
      findAllPropsByGameId(selectedGameId);
    });
  });

  $("#savePropForm").submit(function (event) {
    event.preventDefault();
    const prop = {
      propName: $("#propName").val(),
      propRarity: $("#propRarity").val(),
      propDescription: $("#propDescription").val(),
      propImageId: $("#propImageId").val(),
      propTypeId: $("#propTypeId").val(),
      gameId: $("#SelectGame").val(),
      createdTime: new Date().toISOString(),
    };
    saveProp(prop);
  });
});

var propTypesMap = {};

function loadGames() {
  axios
    .get("http://localhost:8080/PlayCentric/prop/findAllGame")
    .then(function (res) {
      const games = res.data;
      const selectGame = $("#SelectGame");
      selectGame.empty();
      games.forEach(function (game) {
        const option = $("<option></option>")
          .val(game.gameId)
          .text(game.gameName);
        selectGame.append(option);
      });

      if (games.length > 0) {
        selectGame.val(games[0].gameId);
        $("#submitBtn").click();
      }
    })
    .catch(function (error) {
      console.error("Error fetching game data:", error);
    });
}

function loadAllPropTypes() {
  axios
    .get("http://localhost:8080/PlayCentric/prop/findAllPropTypes")
    .then(function (res) {
      const propTypes = res.data;
      propTypes.forEach(function (propType) {
        propTypesMap[propType.propTypeId] = propType.propType;
      });
    })
    .catch(function (error) {
      console.error("Error fetching all prop types data:", error);
    });
}

function fetchPropTypesByGameId(gameId, callback) {
  axios
    .get("http://localhost:8080/PlayCentric/prop/findAllPropTypesByGameId", {
      params: { gameId: gameId },
    })
    .then((res) => {
      callback(res.data);
    })
    .catch((error) => {
      console.error("Error fetching prop types data:", error);
    });
}

function populatePropTypeSelect(propTypes) {
  const propTypeSelect = $("#propTypeId");
  propTypeSelect.empty();
  propTypes.forEach(function (propType) {
    const option = $("<option></option>")
      .val(propType.propTypeId)
      .text(propType.propType);
    propTypeSelect.append(option);
  });
}

function findAllPropsByGameId(gameId) {
  axios
    .get("http://localhost:8080/PlayCentric/prop/findAllPropsByGameId", {
      params: { gameId: gameId },
    })
    .then((res) => {
      const props = res.data;
      table.clear();
      props.forEach(function (prop) {
        const row = [
          prop.propId,
          prop.propName,
          `<img src="${prop.propImageId}" alt="${prop.propImageId}" width="50">`,
          propTypesMap[prop.propTypeId] || "未知",
          prop.propRarity,
          prop.propDescription,
          prop.createdTime,
          prop.updatedTime,
          '<button class="btn btn-primary">修改</button>',
          '<button class="btn btn-danger">刪除</button>',
        ];
        table.row.add(row);
      });
      table.draw();
    })
    .catch((error) => {
      console.error("Error fetching props data:", error);
    });
}

function saveProp(prop) {
  axios
    .post("http://localhost:8080/PlayCentric/prop/saveProp", prop)
    .then((res) => {
      console.log("道具已成功保存", res);
      $("#savePropModal").modal("hide");
      findAllPropsByGameId($("#SelectGame").val());
    })
    .catch((error) => {
      console.error("保存道具時出錯", error);
    });
}

function savePropWindow() {
  $("#savePropModal").modal("show");
}
