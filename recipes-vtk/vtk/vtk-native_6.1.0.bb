DESCRIPTION = "The Visualisation Toolkit"
HOMEPAGE = "http://www.vtk.org"
SECTION = "libs"
LICENSE = "CLOSED"
# LICENSE = "VTK-License"
# LICENSE_FLAGS = "license_${PN}_${PV}"
# LIC_FILES_CHKSUM = "file://Copyright.txt;md5=398284bbebadd05b0563a5c63cdbc164"

DEPENDS = "cmake-native"

PVMAJOR = "6.1"

SRC_URI = "http://www.vtk.org/files/release/${PVMAJOR}/VTK-${PV}.tar.gz \
file://0001-vtkCompileTools-Add-a-CompileTools-group.patch"

S = "${WORKDIR}/VTK-${PV}"

SRC_URI[md5sum] = "25e4dfb3bad778722dcaec80cd5dab7d"
SRC_URI[sha256sum] = "bd7df10a479606d529a8b71f466c44a2bdd11fd534c62ce0aa44fad91883fa34"

inherit native cmake

EXTRA_OECMAKE = " \
-DCMAKE_CROSSCOMPILING:BOOL=OFF \
-DCMAKE_AR:PATH=${AR} \
-DBUILD_DOCUMENTATION:BOOL=OFF \
-DBUILD_EXAMPLES:BOOL=OFF \
-DBUILD_SHARED_LIBS:BOOL=ON \
-DBUILD_TESTING:BOOL=OFF \
-DCMAKE_BUILD_TYPE:STRING=Release \
-DVTK_BUILD_ALL_MODULES:BOOL=OFF \
-DVTK_Group_Imaging:BOOL=OFF \
-DVTK_Group_MPI:BOOL=OFF \
-DVTK_Group_Qt:BOOL=OFF \
-DVTK_Group_Rendering:BOOL=OFF \
-DVTK_Group_StandAlone:BOOL=OFF \
-DVTK_Group_Tk:BOOL=OFF \
-DVTK_Group_Views:BOOL=OFF \
-DVTK_Group_Web:BOOL=OFF \
-DVTK_Group_CompileTools:BOOL=ON \
-DVTK_USE_X:BOOL=OFF \
"

do_compile() {
    oe_runmake vtkCompileTools
}

do_install_append() {
    sed -i -e 's:${B}/bin:${STAGING_BINDIR_NATIVE}:' ${B}/VTKCompileToolsConfig.cmake
    install -m 0644 ${B}/VTKCompileToolsConfig.cmake ${D}${libdir}/cmake/vtk-${PVMAJOR}
}

BBCLASSEXTEND = "native"
