document.addEventListener("DOMContentLoaded", function () {


    const tr = document.querySelectorAll("#alls")

    const btn1 = document.querySelector("#but1")
    const btn2 = document.querySelector("#but2")


    function id() {
        for (let x = 0; x < tr.length; x++) {
            const z = document.querySelectorAll("#alls")[x]
           console.log(tr.dataset.roles)
            if (z.dataset.roles === "2") {
                tr.classList.add("text-danger")
                btn1.classList.remove("btn-outline-dark")
                btn2.classList.remove("btn-outline-dark")
                btn1.classList.add("btn-outline-danger")
                btn2.classList.add("btn-outline-danger")
            }
        }
    }
id();

});