const result = document.querySelector('.result');
const type = document.querySelector('.type');
type.addEventListener('change', (e) => {
    let url = `http://localhost:8080/PlayCentric/anno/getOneTypeAnno`;
    if (type.value != 0) {
        axios.get(url, {
            params: {
                typeId: type.value
            }
        })
            .then(res => {
                result.innerHTML = '';
                res.data.forEach(elm => {
                    htmlMaker(elm);
                });
            })
            .catch(err => {
                console.error(err);
            })
    }
    if (type.value == 0) {
        url = 'http://localhost:8080/PlayCentric/anno/getAllAnno';
        axios.get(url)
            .then(res => {
                result.innerHTML = '';
                res.data.forEach(elm => {
                    htmlMaker(elm);
                });
            })
            .catch(err => {
                console.error(err);
            })
    }
})

function htmlMaker(data) {
    result.innerHTML += `
				<div class="h-auto col-span-5 text-left text-2xl text-violet-500 font-bold">
					<a href="/PlayCentric/anno/showOneAnno?annoId=${data.annoId}"
						>[${data.announcementType.annoTypeName}]${data.title}</a>
						</div>
						<div class="h-auto col-span-5 text-right text-2xl">
							<span>${data.createAt}</span>
						</div>
						<div class="col-span-2"></div>
			`;

}