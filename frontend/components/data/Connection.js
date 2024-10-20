class Connection {
    constructor() {
        this.defaultUrl = "http://localhost:8081/api/";
    }

    async getStudents() {
        const response = await fetch(this.getUrl("enrollments"));
        this.checkResponse(response);
        return await response.json(); 
    }

    async getStudentById(studentId) {
        const response = await fetch(this.getUrl(`students/${studentId}`));
        this.checkResponse(response);
        return await response.json();
    }

    async updateStudent(studentId, student) {
        const response = await fetch(this.getUrl(`students/${studentId}`), {
            method: "PUT",
            headers: {
                "Content-Type": "application/json", 
            },
            body: JSON.stringify(student),
        });
        this.checkResponse(response);
    }

    async getCourses() {
        const response = await fetch(this.getUrl("courses"));
        this.checkResponse(response);
        return await response.json();
    }

    async deleteEnrolment(id) {
        const response = await fetch(this.getUrl(`enrollments/${id}`), {
            method: "DELETE",
        });
        this.checkResponse(response);
    }

    async addStudent(student) {
        const response = await fetch(this.getUrl("students"), {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(student),
        });
        this.checkResponse(response);
        return await response.json();
    }

    async addEnrolment(studentId, selectedCourseId) {
        const response = await fetch(this.getUrl("enrollments"), {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ studentId: studentId, courseId: selectedCourseId }),
        });
        this.checkResponse(response);
    }

    getUrl(url) {
        return this.defaultUrl + url;
    }

    checkResponse(response) {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
    }
}

export default Connection;
