const backendIP='http://localhost:3002/'


export const getBackendUrl={
    register:backendIP+'customer/register',
    login:backendIP+'customer/login',
    customerList:backendIP+'customer/',
    customerCount:backendIP+'customer/users-count',
    customerLastFiveUser:backendIP+'customer/users-last',
    addCar:backendIP+'vehicle/add',
    cars:backendIP+'vehicle/',
    adminLogin:backendIP+'customer/login-admin'
}
