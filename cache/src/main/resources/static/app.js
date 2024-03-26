
const cache = new Map()

let g = 0

function CalcFactory () {

    // const cache = new Map()

    return function (a,b,c) {

        g++

        const key = "" + a + b + c

        if (!cache.has(key)) {

            console.log('calculation start ...')

            let res = 0
            for (let i = 0; i < 1000000000; i++) {
                res += i * 589
            }

            cache.set(key, a + b + c + g)

            console.log('calculation end')
        }

        return cache.get(key)
    }
}

const test1 = CalcFactory()
const test2 = CalcFactory()

test1(2, 3, 3)