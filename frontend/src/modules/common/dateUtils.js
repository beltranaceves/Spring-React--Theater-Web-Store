export const addDaysToDate = (date, days) => {
    let newDate = new Date(date);
    newDate.setDate(date.getDate() + days);
    return newDate;
}

export const formatDate = (date) => {
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    return `${year}-${month<10?`0${month}`:`${month}`}-${day<10?`0${day}`:`${day}`}`
}

export const getArrayWithNextDays = (numberOfDays) => {
    const dates = [];
    const currentDate = new Date();
    let index;
    for (index=1; index < numberOfDays; index++) {
        dates[index] = addDaysToDate(currentDate, index);
    }
    return dates;
}